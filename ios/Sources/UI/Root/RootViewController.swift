import UIKit

class RootViewController: BaseViewController {
    private var mainView: RootView {
        return self.view as! RootView
    }
    
    private var dataSource: DataSource

    init() {
        dataSource = DataSource()
        super.init(nibName: nil, bundle: nil)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = RootView()
        mainView.delegate = self
    }
}

extension RootViewController: RootViewDelegate {
    func didTapAddIngredient() {
        present(viewControllerFactory.ingredientsPickerViewController(), animated: true) {
            self.dataSource.getIngredients()
            self.mainView.addIngredient()
        }
    }

    func didTapServe() {
        present(viewControllerFactory.resultViewController(), animated: true) {
            self.mainView.resetBlender()
        }
    }
}
