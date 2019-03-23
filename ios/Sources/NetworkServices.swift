//
//  NetworkServices.swift
//  MixParadise
//
//  Created by Morteza Ahmadi on 2019-03-23.
//  Copyright © 2019 Mirego. All rights reserved.
//

import Foundation

class NetworkServices {
    let url = URL(string: "https://mirego-csgames19.herokuapp.com/ingredients")
    
    private static var shared : NetworkServices = {
        let sharedInstance = NetworkServices.init()
        return sharedInstance
    }()
    
    class func sharedInstance() -> NetworkServices {
        return shared
    }
    
    private init() { }
    
    func getIngeredients(completionHandler: @escaping (Model) -> ()) {
        guard let url = url else { return }
        var request = URLRequest(url: url)
        request.setValue("LostAtC", forHTTPHeaderField: "Team")
        URLSession.shared.dataTask(with: request) { (data, response, err) in
            guard err == nil else { return }
            guard let data = data else { return }
            do {
                let dataSource = try JSONDecoder().decode(Model.self, from: data)
                completionHandler(dataSource)
            } catch {
                print(error)
            }
        }.resume()
    }
}
