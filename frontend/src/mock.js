
// const Mock = require('mockjs')
// const Random = Mock.Random

// let Result = {
//     code: 200,
//     message: 'sucess',
//     data: null
// }

// Mock.mock('/accountexist', 'get', () =>{
//     Result.data = {
//         token: Random.string(32),
//         // accountexist: Random.email()
//         accountexist: 'asdasd'
//     }
//     return Result
// })

// Mock.mock('/signup', 'post', () =>{

//     Result.code = 400
//     Result.message = "no authorization"
//     return Result
// })