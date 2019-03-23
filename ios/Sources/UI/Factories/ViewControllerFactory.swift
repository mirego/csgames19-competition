class ViewControllerFactory {
    init() {
    }

    func rootViewController() -> RootViewController {
        return assign(RootViewController())
    }

    func ingredientsPickerViewController() -> IngredientsPickerViewController {
        return assign(IngredientsPickerViewController())
    }

    func resultViewController() -> ResultViewController {
        return assign(ResultViewController())
    }
}

private extension ViewControllerFactory {
    func assign<T: BaseViewController>(_ viewController: T) -> T {
        viewController.viewControllerFactory = self
        return viewController
    }
}
