import UIKit

class IngredientsPickerViewController: BaseViewController {
    
    private var dataSource: DataSource
    
    private var mainView: IngredientsPickerView {
        return self.view as! IngredientsPickerView
    }

    init() {
        dataSource = DataSource()
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
        self.dataSource.setView(view: self)
        self.dataSource.getSecretIngredients()
    }
    
    func updateView(data: NSDictionary) {
        mainView.isLoading = false
        print(data)
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
