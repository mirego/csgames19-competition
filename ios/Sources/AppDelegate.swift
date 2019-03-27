import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    private let viewModelFactory = ViewModelFactory(ingredientsWebService: IngredientsWebService(), blenderService: BlenderService())
    private var viewControllerFactory: ViewControllerFactory!

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        viewControllerFactory = ViewControllerFactory(viewModelFactory: viewModelFactory)

        window = UIWindow(frame: UIScreen.main.bounds)
        window!.backgroundColor = .white
        window!.rootViewController = viewControllerFactory.rootViewController()
        window!.makeKeyAndVisible()

        return true
    }
}
