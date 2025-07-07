import UIKit
import library

class ViewController: UIViewController {
    private var composeViewController: UIViewController?

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white

        // Add the Compose UI TestView
        setupComposeView()
    }

    private func setupComposeView() {
        // Create the Compose UI view controller
        composeViewController = ComposeUIHelperKt.createTestViewController()

        if let composeVC = composeViewController {
            // Add as a child view controller
            addChild(composeVC)

            // Add the view and set its frame
            composeVC.view.frame = CGRect(x: 0, y: 0, width: view.bounds.width, height: view.bounds.height)
            view.addSubview(composeVC.view)

            // Complete the addition
            composeVC.didMove(toParent: self)
        }
    }
}
