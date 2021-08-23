import axios from "axios";
import router from "./router";
import Element from "element-ui";

axios.defaults.baseURL = "http://localhost:9000"

const request = axios.create ({
    timeout: 5000,
    headers: {
        'Content-Type' : "application/json; charset=utf-8"
    }
})

request.interceptors.request.use(
    config => {
        config.headers['Authorization'] = localStorage.getItem("token")
        return config
    }
)

request.interceptors.response.use(
    console.log(+"asdasdasd"),
    response =>{
        console.log("response ->" + response)
        let res = response.data
        console.log("res ->" + res)
        if(res.statusCode === 201 || res.statusCode === 200){
            console.log("success")
            Element.Message.success("Success")
            return res
        }else if(res.statusCode === 400){
            console.log("Email was taken")
            Element.Message.error("Email was taken")
            return Promise.reject(res.data.message)
        }else if(res.statusCode === 500){
            console.log("Incorrect password/email")
            Element.Message.error("Incorrect password/email")
            return Promise.reject(res.data.message)
        }else{
            console.log("response ->" + response)
            console.log("res ->" + res)
            Element.Message.error(res.message ? 'error':res.message)
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