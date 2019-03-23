import UIKit

class IngredientsPickerViewController: BaseViewController {
    let service: IngredientService

    private var mainView: IngredientsPickerView {
        return self.view as! IngredientsPickerView
    }

    init(_ service: IngredientService) {
        super.init(nibName: nil, bundle: nil)

        self.service = service
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
        self.service.getList { (ingredients, error) in
            guard error == nil else {
                let alert = UIAlertController(title: "Error", message: "Could not get your paradise, mix again later.", preferredStyle: UIAlertController.Style.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
                self.present(alert, animated: true, completion: nil)
            }

            self.mainView.configure(ingredients: ingredients!)
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
}
