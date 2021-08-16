import axios from './config.js'
import domain from './domain.js'

const qs = require('qs');

export default {
    data:"",
    userlogin:function(data){
        return axios.post(`${domain.url}/users/login`,data);
    },
    standardcurdPost:function(name,data){
        return axios.post(`${domain.devurl}/${name}`,data);
    },
    standardcurdGet:function(name,data){
        return axios.get(`${domain.devurl}/${name}`,{
            params:data,
            paramsSerializer: function(params){
                return qs.stringify(params, {arrayFormat: 'repeat'});
            }
        });
    }
}