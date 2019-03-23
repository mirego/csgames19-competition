import UIKit

class RootViewController: BaseViewController {
    private var mainView: RootView {
        return self.view as! RootView
    }
    
    init() {
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

extension RootViewController: RootViewDelegate, IngredientsPickerViewDelegate {
    func didTapCloseButton() {
        
    }
    
    func addIngredient(juice: Juice?) {
        self.mainView.addIngredient(juice: juice)
    }
    
    
    func didTapAddIngredient() {
        let controller = viewControllerFactory.ingredientsPickerViewController()
        present(controller, animated: true) {
            
        }
    }

    func didTapServe() {
        present(viewControllerFactory.resultViewController(), animated: true) {
            self.mainView.resetBlender()
        }
    }
}
