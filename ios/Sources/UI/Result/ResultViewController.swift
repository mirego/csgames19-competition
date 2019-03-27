import UIKit

class ResultViewController: BaseViewController {
    private var mainView: ResultView {
        return self.view as! ResultView
    }

    private let viewModel: ResultPageViewModel

    init(viewModel: ResultPageViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)

        modalPresentationStyle = .custom
        transitioningDelegate = self
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = ResultView()
        mainView.delegate = self
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        mainView.isLoading = true

        viewModel.getResult { [weak self] (result) in
            self?.mainView.isLoading = false
            switch result {
            case .Error(let error):
                print("Error: \(error)")
            case .Succes(let resultViewModel):
                self?.mainView.configure(viewModel: resultViewModel)
            }
        }
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        mainView.closeView()
    }
}

extension ResultViewController: ResultViewDelegate {
    func didTapNewDrink() {
        dismiss(animated: true, completion: nil)
    }
}

extension ResultViewController: UIViewControllerTransitioningDelegate {
    func presentationController(forPresented presented: UIViewController, presenting: UIViewController?, source: UIViewController) -> UIPresentationController? {
        return CustomPresentationController(presentedViewController: presented, presentingViewController: presenting, layoutType: .center, tapAroundEnabled: false)
    }

    func animationController(forPresented presented: UIViewController, presenting: UIViewController, source: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        return PopUpTransition(presentationType: .presenting)
    }

    func animationController(forDismissed dismissed: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        return PopUpTransition(presentationType: .dismissing)
    }
}
