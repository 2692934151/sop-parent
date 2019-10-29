var app = new Vue({
    el: '#app',
    data: {
        projects: [],
        projectDefault: "",
        InterfaceAndMenu: [],
        menuDefault: "",
        interfaceDefault: "",
        interfaceDetail: "",
        reColumns: [
            {
                title: '名称',
                key: 'interfaceAttr'
            },
            {
                title: '类型',
                key: 'type'
            },
            {
                title: '是否必须',
                key: 'isMust'
            },
            {
                title: '默认值',
                key: 'defaultData'
            },
            {
                title: '备注',
                key: 'description'
            },
            {
                title: '其他信息',
                key: 'otherInfo'
            }
        ],
        headerColumns: [
            {
                title: '参数名称',
                key: 'name'
            },
            {
                title: '参数值',
                key: 'value'
            },
            {
                title: '是否必须',
                key: 'required'
            },
            {
                title: '示例',
                key: 'example'
            },
            {
                title: '备注',
                key: 'desc'
            }
        ],
        errorColumns: [
            {
                title: '错误码',
                key: 'interfaceAttr'
            },
            {
                title: '错误描述',
                key: 'description'
            },
            {
                title: '解决方案',
                key: 'defaultData'
            }
        ]
    },
    created() {
        this.init();
    },
    methods: {
        init() {
            this.getProject();
        },
        turnProject(token,id){
            this.InterfaceAndMenu(token,id);
        },
        getProject() {
            axios.get("http://localhost:8083/project/get").then(res => {
                this.projects = res.data
                this.projectDefault = res.data[0];
            }).catch(function (error) {
                console.log(error);
            })
        },
        getMenuAndInterface(token, projectId) {
            axios.get("http://localhost:8083/InterfaceAndMenu", {
                params: {
                    token: token,
                    projectId: projectId
                }
            }).then(res => {
                this.InterfaceAndMenu = res.data
            }).catch(function (error) {
                console.log(error);
            })
        },
        getInterfaceDetail(token, interfaceId) {
            axios.get("http://localhost:8083/getInterfaceDetail", {
                params: {
                    token: token,
                    id: interfaceId
                }
            })
                .then(res => this.interfaceDetail = res.data)
                .catch(err => console.log(err))
        },
        getInterface(msg) {
            this.getInterfaceDetail(this.projectDefault.token, msg);
        }
    },
    computed: {},
    watch:{
        projectDefault(newValue,oldValue){
            // console.log(newValue.projectId)
            let projectId = newValue.projectId;
            let token = newValue.token;
            this.getMenuAndInterface(token, projectId);
        },
        InterfaceAndMenu(newValue,oldValue){
            let token = this.projectDefault.token;
            let interfaceId = newValue[0].list[0].id;
            this.interfaceDefault=interfaceId;
            this.getInterfaceDetail(token, interfaceId);
        }
    }
})