import UIKit

extension UIColor {
    class var coolTeal: UIColor {
        return UIColor(red: 91.0 / 255.0, green: 188.0 / 255.0, blue: 182.0 / 255.0, alpha: 1.0)
    }

    class var turquoise: UIColor {
        return UIColor(red: 0.0, green: 171.0 / 255.0, blue: 172.0 / 255.0, alpha: 1.0)
    }

    class var darkGreyBlue: UIColor {
        return UIColor(red: 43.0 / 255.0, green: 50.0 / 255.0, blue: 81.0 / 255.0, alpha: 1.0)
    }

    class var darkBlueGrey: UIColor {
        return UIColor(red: 32.0 / 255.0, green: 73.0 / 255.0, blue: 85.0 / 255.0, alpha: 1.0)
    }

    class var rouge: UIColor {
        return UIColor(red: 180.0 / 255.0, green: 28.0 / 255.0, blue: 74.0 / 255.0, alpha: 1.0)
    }

    class var veryLightPink: UIColor {
        return UIColor(white: 238.0 / 255.0, alpha: 1.0)
    }

    class var slateGrey: UIColor {
        return UIColor(red: 88.0 / 255.0, green: 89.0 / 255.0, blue: 91.0 / 255.0, alpha: 1.0)
    }

    class var grapefruit: UIColor {
        return UIColor(red: 241.0 / 255.0, green: 103.0 / 255.0, blue: 95.0 / 255.0, alpha: 1.0)
    }

    class var grapefruitHighlighted: UIColor {
        return UIColor(red: 186.0 / 255.0, green: 59.0 / 255.0, blue: 51.0 / 255.0, alpha: 1.0)
    }

    class var oysterBayBlue: UIColor {
        return UIColor(red: 223.0 / 255.0, green: 250.0 / 255.0, blue: 254.0 / 255.0, alpha: 1.0)
    }

    class var lightWhite: UIColor {
        return UIColor(red: 245.0 / 255.0, green: 245.0 / 255.0, blue: 245.0 / 255.0, alpha: 1.0)
    }

    class var topaz: UIColor {
        return UIColor(red: 18.0 / 255.0, green: 174.0 / 255.0, blue: 174.0 / 255.0, alpha: 1.0)
    }

    class var dullYellow: UIColor {
        return UIColor(red: 223.0 / 255.0, green: 211.0 / 255.0, blue: 108.0 / 255.0, alpha: 1.0)
    }

    class var darkPeach: UIColor {
        return UIColor(red: 223.0 / 255.0, green: 116.0 / 255.0, blue: 111.0 / 255.0, alpha: 1.0)
    }

}

enum Headline {
    case H1
    case H2
    case H3
    case H4
    case H5

    func font(withStyle style: FontStyle = .regular) -> UIFont {
        return style.font(withSize: fontSize)
    }

    private var fontSize: CGFloat {
        switch self {
        case .H1:
            return 30
        case .H2:
            return 18
        case .H3:
            return 14
        case .H4:
            return 13
        case .H5:
            return 12
        }
    }
}

enum FontStyle {
    case light
    case regular
    case semiBold
    case bold

    func font(withSize size: CGFloat) -> UIFont {
        switch self {
        case .light:
            return UIFont(name: "Montserrat-Light", size: size)!
        case .regular:
            return UIFont(name: "Montserrat-Regular", size: size)!
        case .semiBold:
            return UIFont(name: "Montserrat-SemiBold", size: size)!
        case .bold:
            return UIFont(name: "Montserrat-Bold", size: size)!
        }
    }
}
