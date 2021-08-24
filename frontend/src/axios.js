import axios from "axios";
import router from "./router";
import Element from "element-ui";

axios.defaults.baseURL = "http://localhost:9000"

const request = axios.create ({
    timeout: 5000,
    headers: {
        // 'Content-Type' : "application/json;"
        'Content-Type' : "application/json; charset=utf-8"
        // 'Content-Type' : "application/x-www-form-urlencoded; application/json; charset=utf-8"
    }
})

request.interceptors.request.use(
    config => {
        config.headers['authentication'] = localStorage.getItem("token")
        console.log(config.headers)
        return config
    }
)

request.interceptors.response.use(
    response =>{
        console.log(response)
        let res = response.data
        if(res.statusCode === 201 || res.statusCode === 200){
            Element.Message.success(res.message)
            return response
        }else if(res.statusCode === 400){
            Element.Message.error(res.message)
            return Promise.reject(res.data.message)
        }else if(res.statusCode === 401){
            Element.Message.error(res.message)
            return Promise.reject(res.message)
        }else if(res.statusCode === 500){
            Element.Message.error(res.message)
            return Promise.reject(res.message)
        }else{
            console.log("error code " + res.statusCode)
            Element.Message.error("error message: " + res.message)
            return Promise.reject(res.message)
        }

    }, 

    // error =>{
    
    //     console.log(error)

    //     if(error.response.data){
    //         error.Message = error.response.data.Message
    //     }
    //     if(error.response.statusCode === 401){
    //         router.push("/signin")
    //     }

    //     Element.Message.error(error.Message, {duration:3000})

    //     return Promise.reject(error)
    // }
    

)

export default request