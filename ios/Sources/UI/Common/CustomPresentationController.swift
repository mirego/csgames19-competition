import UIKit
import MRGTaylor

class CustomPresentationController: UIPresentationController {
    enum LayoutType {
        case bottom
        case center
    }

    private let layoutType: LayoutType
    private let tapAroundEnabled: Bool
    private let dimmingView = UIView()

    init(presentedViewController: UIViewController, presentingViewController: UIViewController?, layoutType: LayoutType, tapAroundEnabled: Bool = true) {
        self.layoutType = layoutType
        self.tapAroundEnabled = tapAroundEnabled
        super.init(presentedViewController: presentedViewController, presenting: presentingViewController)
    }

    override var shouldPresentInFullscreen: Bool {
        return false
    }

    override var presentedView: UIView? {
        let presentedView = super.presentedView
        // HACK: This is a workaround for the bug described in: http://openradar.appspot.com/18005149
        presentedView?.frame = frameOfPresentedViewInContainerView
        return presentedView
    }

    override func presentationTransitionWillBegin() {
        guard let containerView = containerView else { return }

        if tapAroundEnabled {
            dimmingView.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(didTapAroundPopup)))
        }

        dimmingView.backgroundColor = UIColor.black.withAlphaComponent(0.52)
        dimmingView.alpha = 0
        containerView.addSubview(dimmingView)
        animateDimingView(isPresenting: true)

        if let presentedView = presentedView {
            presentedView.layer.cornerRadius = 20
            presentedView.layer.applySketchShadow(color: .black, alpha: 0.07, x: 0, y: -6, blur: 4, spread: 0)
            presentedView.layer.shadowPath = UIBezierPath(roundedRect: presentedView.bounds, cornerRadius: presentedView.layer.cornerRadius).cgPath
        }
    }

    override func dismissalTransitionWillBegin() {
        animateDimingView(isPresenting: false)
    }

    override func containerViewWillLayoutSubviews() {
        super.containerViewWillLayoutSubviews()

        guard let containerView = containerView else { return }

        dimmingView.frame = containerView.bounds
        presentedViewController.view.frame = frameOfPresentedViewInContainerView
    }

    override var frameOfPresentedViewInContainerView: CGRect {
        guard let containerView = containerView else { return .zero }

        switch layoutType {
        case .bottom:
            let presentedViewHeight = containerView.height * 0.8
            return CGRect(origin: CGPoint(x: 0, y: containerView.height - presentedViewHeight),
                          size: CGSize(width: containerView.width, height: presentedViewHeight))
        case .center:
            let sideMargin = 16.f
            let presentedViewHeight = min(518.f, containerView.height - 30)
            return CGRect(origin: CGPoint(x: sideMargin, y: (containerView.height - presentedViewHeight) / 2),
                          size: CGSize(width: containerView.width - 2 * sideMargin, height: presentedViewHeight))
        }
    }

    private func animateDimingView(isPresenting: Bool) {
        guard let transitionCoordinator = presentedViewController.transitionCoordinator else { return }
        transitionCoordinator.animate(alongsideTransition: { _ in
            self.dimmingView.alpha = isPresenting ? 1 : 0
        }, completion: nil)
    }
}

extension CustomPresentationController {
    @objc
    private func didTapAroundPopup() {
        presentedViewController.dismiss(animated: true, completion: nil)
    }
}
