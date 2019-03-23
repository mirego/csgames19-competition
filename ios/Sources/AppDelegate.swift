import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    private var viewControllerFactory: ViewControllerFactory!

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        viewControllerFactory = ViewControllerFactory()

        window = UIWindow(frame: UIScreen.main.bounds)
        window!.backgroundColor = .white
        window!.rootViewController = viewControllerFactory.rootViewController()
        window!.makeKeyAndVisible()

        return true
    }
}
