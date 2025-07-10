// module.exports = {
//   project: {
//     ios: {
//       automaticPodsInstallation: true,
//     },
//   },
// };


const path = require('path');

module.exports = {
  project: {
    ios: {
      automaticPodsInstallation: true,
    },
  },
  dependencies: {
    'rn-lib': {
      root: path.join(__dirname, '..'),
      platforms: {
        android: {
          sourceDir: '../android',
          packageImportPath: 'import com.rnlib.RnLibViewPackage;',
        },
        ios: {
          podspecPath: '../RnLib.podspec',
        },
      },
    },
  },
};
