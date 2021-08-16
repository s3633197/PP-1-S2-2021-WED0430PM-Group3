import axios from 'axios'
import {ElLoading, ElMessage} from 'element-plus'
import router from '../router/index.js'

let Loading

function startLoading () {
    Loading = ElLoading.service({
        lock:true,
        text:'加载中....',
        background:'rgba(0,0,0,0.7)',
        fullscreen:true
    })
}

function endLoading(){
    Loading.close();
}

axios.interceptors.request.use(
    (config) => {
        startLoading();
        //console.log(localStorage.getItem('token'));
        if(localStorage.getItem('token')){
            config.headers.Authorization ='Token '+ localStorage.getItem('token');
            //console.log(config.headers.Authorization)
        }
        //config.headers.Authorization = 'Token 7b355a10f11fe6ab335517b7e6dbd6fded4565c8';
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
)

axios.interceptors.response.use(
    (response) => {
        endLoading();
        if(response.data.status == "failed"){
            ElMessage.error(`${response.data.code}:${response.data.msg}`);
        }else if(response.data.status == "OK"){
            ElMessage.success(`${response.data.code}:${response.data.msg}`);
        }
        return Promise.resolve(response);
    },
    (error) => {
        //ElMessage.error(error.response.data);
        endLoading();

        if(error.response){
            const { status } = error.response;
            if (status === 401) {
                ElMessage.error('请重新登录');
                localStorage.removeItem('token');
                router.push('/login');
            }else if(status == 404){
                ElMessage.error('404:资源不存在');
            }else if(status == 503){
                ElMessage.error('503:操作不允许');
            }
        }else{
            ElMessage.error("网络未连接，请检查网络");
        }
        return Promise.reject(error);
    }
)

export default axios;