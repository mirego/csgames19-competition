import UIKit

class PopUpTransition: NSObject, UIViewControllerAnimatedTransitioning {
    enum PresentationType {
        case presenting
        case dismissing
    }

    private let presentationType: PresentationType

    init(presentationType: PresentationType) {
        self.presentationType = presentationType
    }

    func transitionDuration(using transitionContext: UIViewControllerContextTransitioning?) -> TimeInterval {
        return 0.5
    }

    func animateTransition(using transitionContext: UIViewControllerContextTransitioning) {
        let containerView = transitionContext.containerView
        let springDamping: CGFloat = 0.7
        let initialVelocity: CGFloat = 0.7
        let animationDuration: TimeInterval = transitionDuration(using: transitionContext)

        if let fromViewController = transitionContext.viewController(forKey: UITransitionContextViewControllerKey.from),
            let toViewController = transitionContext.viewController(forKey: UITransitionContextViewControllerKey.to) {

            switch presentationType {
            case .presenting:
                toViewController.view.frame = transitionContext.finalFrame(for: toViewController)
                containerView.addSubview(toViewController.view)
                toViewController.view.transform = CGAffineTransform(scaleX: 0.2, y: 0.2)
                toViewController.view.alpha = 0

                UIView.animate(withDuration: animationDuration, delay: 0, usingSpringWithDamping: springDamping, initialSpringVelocity: initialVelocity, options: [.beginFromCurrentState, .curveEaseOut], animations: { () -> Void in
                    toViewController.view.transform = CGAffineTransform.identity
                    toViewController.view.alpha = 1
                }, completion: { (_) -> Void in
                    transitionContext.completeTransition(true)
                })
            case .dismissing:
                UIView.animate(withDuration: animationDuration, delay: 0, usingSpringWithDamping: springDamping, initialSpringVelocity: initialVelocity, options: [.beginFromCurrentState, .curveEaseIn], animations: { () -> Void in
                    fromViewController.view.transform = CGAffineTransform(scaleX: 0.2, y: 0.2)
                    fromViewController.view.alpha = 0
                }, completion: { (_) -> Void in
                    transitionContext.completeTransition(true)
                })
            }
        }
    }
}
