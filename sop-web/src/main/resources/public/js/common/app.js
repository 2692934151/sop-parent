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
                key:'name'
            },
            {
                title: '参数值',
                key:'value'
            },
            {
                title: '是否必须',
                key:'required'
            },
            {
                title: '示例',
                key:'example'
            },
            {
                title:'备注',
                key:'desc'
            }
        ]
    },
    created() {
        let project = this.getProject();
        // let token = project.token;
        let token = "7662108c9615f68724ba923e0b4b7e7c909d62341391bc43f7c0b7d4424fd9ff";
        // let projectId = project.projectId;
        let projectId = 30;
        let interfaceId = 53;
        this.getMenuAndInterface(token, projectId);
        this.getInterfaceDetail(token, interfaceId);
    },
    methods: {
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
                this.menuDefault = res.data[0];
                this.interfaceDefault = res.data[0].list[0];
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
        getInterface(msg){
            console.log(msg);
            this.getInterfaceDetail("7662108c9615f68724ba923e0b4b7e7c909d62341391bc43f7c0b7d4424fd9ff",msg);
        }
    },
    computed: {
    }
})