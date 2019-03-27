import UIKit

class IngredientsPickerViewController: BaseViewController {
    private var mainView: IngredientsPickerView {
        return self.view as! IngredientsPickerView
    }

    private let viewModel: IngredientsPickerViewModel

    init(viewModel: IngredientsPickerViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)

        modalPresentationStyle = .custom
        transitioningDelegate = self
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = IngredientsPickerView()
        mainView.delegate = self
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        mainView.isLoading = true

        viewModel.getSections { [weak self] (result) in
            self?.mainView.isLoading = false
            switch result {
            case .Error(let error):
                print("Error: \(error)")
            case .Succes(let sections):
                self?.mainView.configure(sections: sections)
            }
        }
    }
}

extension IngredientsPickerViewController: UIViewControllerTransitioningDelegate {
    func presentationController(forPresented presented: UIViewController, presenting: UIViewController?, source: UIViewController) -> UIPresentationController? {
        return CustomPresentationController(presentedViewController: presented, presentingViewController: presenting, layoutType: .bottom)
    }
}

extension IngredientsPickerViewController: IngredientsPickerViewDelegate {
    func didTapCloseButton() {
        dismiss(animated: true, completion: nil)
    }

    func didTap(ingredientViewModel: IngredientViewModel) {
        dismiss(animated: true) {
            self.viewModel.didSelectIngredient(ingredientViewModel: ingredientViewModel)
        }
    }
}
