import Foundation

class ResultViewModel {
    private let serveResponse: ServeResponse

    init(serveResponse: ServeResponse) {
        self.serveResponse = serveResponse
    }

    var grade: Int {
        get {
            return serveResponse.rating.note
        }
    }

    var subTitle: String {
        get {
            return serveResponse.rating.comment
        }
    }

    var tasteResult: Int {
        get {
            return serveResponse.review.taste
        }
    }

    var volumeResult: Int {
        get {
            return serveResponse.review.volume
        }
    }

    var strengthResult: Int {
        get {
            return serveResponse.review.strength
        }
    }
}
