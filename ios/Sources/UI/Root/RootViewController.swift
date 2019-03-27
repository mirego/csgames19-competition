import UIKit

class RootViewController: BaseViewController {
    private var mainView: RootView {
        return self.view as! RootView
    }

    private let rootViewModel: RootViewModel

    init(rootViewModel: RootViewModel) {
        self.rootViewModel = rootViewModel
        super.init(nibName: nil, bundle: nil)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = RootView()
        mainView.delegate = self
        rootViewModel.listener = self
    }
}

extension RootViewController: RootViewDelegate {
    func didTapAddIngredient() {
        present(viewControllerFactory.ingredientsPickerViewController(), animated: true, completion: nil)
    }

    func didTapServe() {
        present(viewControllerFactory.resultViewController(), animated: true, completion: nil)
    }
}

extension RootViewController: RootViewModelListener {
    func didAddIngredient(ingredient: BlenderIngredientViewModel) {
        mainView.addIngredient(with: ingredient)
    }

    func blenderDidReset() {
        mainView.resetBlender()
    }
}
