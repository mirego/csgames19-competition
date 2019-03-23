import UIKit

protocol IngredientsPickerViewControllerDelegate: AnyObject {
    func addIngredient(juice: Juice)
}

class IngredientsPickerViewController: BaseViewController {
    private var mainView: IngredientsPickerView {
        return self.view as! IngredientsPickerView
    }
    
    var delegate: IngredientsPickerViewControllerDelegate?
    
    init() {
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
    }
}

extension IngredientsPickerViewController: UIViewControllerTransitioningDelegate {
    func presentationController(forPresented presented: UIViewController, presenting: UIViewController?, source: UIViewController) -> UIPresentationController? {
        return CustomPresentationController(presentedViewController: presented, presentingViewController: presenting, layoutType: .bottom)
    }
}

extension IngredientsPickerViewController: IngredientsPickerViewDelegate {
    func addIngredient(juice: Juice?) {
        
    }
    
    func didTapCloseButton() {
        dismiss(animated: true, completion: nil)
    }
}
