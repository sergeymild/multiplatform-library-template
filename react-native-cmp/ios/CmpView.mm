#import "CmpView.h"

#import <react/renderer/components/CmpViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/CmpViewSpec/EventEmitters.h>
#import <react/renderer/components/CmpViewSpec/Props.h>
#import <react/renderer/components/CmpViewSpec/RCTComponentViewHelpers.h>

#import "RCTFabricComponentsPlugins.h"
#import <library/library.h>

using namespace facebook::react;

@interface CmpView () <RCTCmpViewViewProtocol>
@property (nonatomic, strong) UIViewController *composeViewController;
@end

@implementation CmpView {
    UIView * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<CmpViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps = std::make_shared<const CmpViewProps>();
    _props = defaultProps;

    _view = [[UIView alloc] init];

    // Create the Compose UI view controller
    self.composeViewController = [LibraryComposeUIHelperKt createTestViewController];

    // Add the Compose UI view to our view
    if (self.composeViewController) {
        UIView *composeView = self.composeViewController.view;
        [_view addSubview:composeView];
        composeView.frame = _view.bounds;
        composeView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
    }

    self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &oldViewProps = *std::static_pointer_cast<CmpViewProps const>(_props);
    const auto &newViewProps = *std::static_pointer_cast<CmpViewProps const>(props);

    if (oldViewProps.color != newViewProps.color) {
        NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.color.c_str()];
        [_view setBackgroundColor:[self hexStringToColor:colorToConvert]];
    }

    [super updateProps:props oldProps:oldProps];
}

- (void)layoutSubviews
{
    [super layoutSubviews];

    // Update the Compose view frame when layout changes
    if (self.composeViewController) {
        self.composeViewController.view.frame = _view.bounds;
    }
}

Class<RCTComponentViewProtocol> CmpViewCls(void)
{
    return CmpView.class;
}

- hexStringToColor:(NSString *)stringToConvert
{
    NSString *noHashString = [stringToConvert stringByReplacingOccurrencesOfString:@"#" withString:@""];
    NSScanner *stringScanner = [NSScanner scannerWithString:noHashString];

    unsigned hex;
    if (![stringScanner scanHexInt:&hex]) return nil;
    int r = (hex >> 16) & 0xFF;
    int g = (hex >> 8) & 0xFF;
    int b = (hex) & 0xFF;

    return [UIColor colorWithRed:r / 255.0f green:g / 255.0f blue:b / 255.0f alpha:1.0f];
}

@end
