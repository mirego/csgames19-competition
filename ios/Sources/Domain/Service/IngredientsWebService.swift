import Foundation

class IngredientsWebService {

    private let baseUrl = "https://mirego-csgames19.herokuapp.com"

    func getIngredients(completion: @escaping (_ result: CallbackResult<IngredientsResponse>) -> ()) {
        guard let request = buildGetIngredientsRequest() else {
            completion(CallbackResult.Error("Invalid url"))
            return
        }

        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let error = error {
                DispatchQueue.main.async { completion(CallbackResult.Error(error.localizedDescription)) }
            } else {
                guard let data = data else {
                    DispatchQueue.main.async { completion(CallbackResult.Error("No/invalid data received")) }
                    return
                }

                do {
                    let recipesResponse = try JSONDecoder().decode(IngredientsResponse.self, from: data)
                    DispatchQueue.main.async { completion(CallbackResult.Succes(recipesResponse)) }
                } catch let error {
                    DispatchQueue.main.async { completion(CallbackResult.Error(error.localizedDescription)) }
                }
            }
        }
        task.resume()
    }

    func validateIngredients(ingredients: [ServeIngredient], completion: @escaping (_ result: CallbackResult<ServeResponse>) -> ()) {
        guard let url = URL(string: baseUrl + "/serve") else  {
            completion(CallbackResult.Error("Invalid url"))
            return
        }
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("Mirego", forHTTPHeaderField: "Team")
        do {
            request.httpBody = try JSONEncoder().encode(ingredients)
            let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
                if let error = error {
                    DispatchQueue.main.async { completion(CallbackResult.Error(error.localizedDescription)) }
                } else {
                    guard let data = data, data.count > 0 else {
                        DispatchQueue.main.async { completion(CallbackResult.Error("No/invalid data received")) }
                        return
                    }
                    do {
                        let serveResponse = try JSONDecoder().decode(ServeResponse.self, from: data)
                        DispatchQueue.main.async { completion(CallbackResult.Succes(serveResponse)) }
                    } catch let error {
                        DispatchQueue.main.async { completion(CallbackResult.Error(error.localizedDescription)) }
                    }
                }
            }
            task.resume()
        } catch let error {
            completion(CallbackResult.Error(error.localizedDescription))
        }
    }

    func getImage(from url: URL, completion: @escaping (UIImage?) -> ()) {
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            guard let data = data, error == nil else {
                DispatchQueue.main.async() { completion(nil) }
                return
            }
            let image = UIImage(data: data)
            DispatchQueue.main.async() { completion(image) }
        }.resume()
    }

    private func buildGetIngredientsRequest() -> URLRequest? {
        let queryParamValue = "ididit"
        var urlComponents = URLComponents(string: baseUrl + "/ingredients")!
        urlComponents.queryItems = [URLQueryItem(name: "key", value: queryParamValue)]

        guard let url = urlComponents.url else { return nil }
        var request = URLRequest(url: url)

        let valueToHash = "csgames19-\(Int(Date().timeIntervalSince1970 / 60))-\(queryParamValue)"
        request.addValue(valueToHash.sha1 ?? "", forHTTPHeaderField: "Authorization")
        request.addValue("Mirego", forHTTPHeaderField: "Team")
        return request
    }
}
