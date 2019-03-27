import Foundation

enum CallbackResult<T> {
    case Succes(T)
    case Error(String)
}
