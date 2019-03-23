import UIKit

class ComponentFactory {
    static func label(for headline: Headline, fontStyle: FontStyle = .regular, textColor: UIColor? = nil) -> UILabel {
        let label = UILabel()
        label.font = headline.font(withStyle: fontStyle)
        label.textColor = textColor
        return label
    }

}
