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
        
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 200, height: 21))
        label.textAlignment = .center
        label.text = "very sad"
        //collectionView.addSubview(label)
//        let collectionView = UICollectionView()
//        for stuff in data {
//            for alcohol in stuff {
//                let label = UILabel(frame: CGRect(x: 0, y: 0, width: 200, height: 21))
//                label.textAlignment = .center
//                label.text = alcohol.label
//                collectionView.addSubview(label)
//            }
//
//        }
        mainView.addSubview(label)
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
