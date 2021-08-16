export default {
    name:"JaContainer",
    type:"JaContainer",
    class: "",
    index: 0,
    parents: ["root"],
    children:[
        {
            name:"default-container",
            type:"container",
            direction:"vertical",
            index:0,
            class:"default-container",
            parents:["root","JaContainer"],
            children:[
                {
                    name:"container",
                    type:"container",
                    index:1,
                    parents:["root","JaContainer","default-container"],
                    children:[
                        {
                            name:"JaMain",
                            type:"main",
                            index:1,
                            parents:["root","JaContainer","default-container","container"],
                            children:[]
                        }
                    ]
                }
            ]
        }
    ]
}