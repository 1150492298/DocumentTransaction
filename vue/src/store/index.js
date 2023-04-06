import Vue from 'vue'
import Vuex from 'vuex'
import request from "@/utils/request";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        departmentList: [],
        roleList: [],
        userForm: {
            createTime: "未知时间",
            departmentId: 1,
            departmentName: "获取部门失败",
            roleId: 1,
            roleName: "获取角色失败",
            userAccount: "获取账户失败",
            userIntro: "获取简介失败",
            userName: "获取名字失败",
            userPhone: "获取手机号失败",
            userSex: 1
        },
        pageDepartmentList: [],
        userRole: [],
        documentClassList:[],
        Cookies: null,
    },
    getters: {
        getDepartmentList(state) {
            return state.departmentList
        },
        getRoleList(state) {
            return state.roleList
        },
        getUserForm(state) {
            return state.userForm
        },
        getUserRole(state) {
            return state.userRole
        },
        getPageDepartmentList(state) {
            return state.pageDepartmentList
        },
        getCookies(state) {
            return state.Cookies
        },
        getDocumentClassList(state) {
            return state.documentClassList
        },
    },
    mutations: {
        setDepartmentList(state, departmentList) {
            state.departmentList = departmentList
        },
        setRoleList(state, roleList) {
            state.roleList = roleList
        },
        setUserForm(state, userForm) {
            state.userForm = userForm
        },
        setUserRole(state, userRole) {
            state.userRole = userRole
        },
        setPageDepartmentList(state, pageDepartmentList) {
            state.pageDepartmentList = pageDepartmentList
        },
        setCookies(state,Cookies) {
            state.Cookies = Cookies
        },
        setDocumentClassList(state,documentClassList) {
            state.documentClassList= documentClassList
        },
    },
    actions: {
        async getDepartmentList(context) {
            await request.get('department/list').then(res => {
                if (res.code === '200') {
                    context.commit('setDepartmentList', res.data)
                } else {
                    console.log("获取部门失败")
                }
            })
        },
        async pageDepartmentList(context, departmentPageRequest) {
            await request.get('department/pageDepartment', {
                params: departmentPageRequest
            }).then(res => {
                if (res.code === '200') {
                    context.commit('setPageDepartmentList', res.data)
                } else {
                    console.log("获取分页部门失败")
                }
            })
        },
        async pageRoleList(context, roleRequest) {
            await request.get('role/pageRoles', {
                params: roleRequest
            }).then(res => {
                if (res.code === '200') {
                    context.commit('setRoleList', res.data)
                } else {
                    console.log("获取角色失败")
                }
            })
        },
        async getRoleList(context) {
            await request.get('role/listRole').then(res => {
                if (res.code === '200') {
                    context.commit('setRoleList', res.data)
                } else {
                    console.log("获取角色失败")
                }
            })
        },
        async getUserFormById(context, userCookie) {
            await request.get("/user/userGetUserByToken/" + userCookie).then(res => {
                if (res.code == '200') {
                    context.commit('setUserForm', res.data)
                } else {
                    this.$notify.error(res.msg)
                }
            })
        },
        async getRightDetailByRoleId(context, roleId) {
            await request.get("/roleRelation/getRightDetailByRoleId/" + roleId).then(res => {
                if (res.code == '200') {
                    context.commit('setUserRole', res.data)
                } else {
                    this.$notify.error(res.msg)
                }
            })
        },
        async getDocumentClassList(context) {
            await request.get("/documentClass/listDocumentClass" ).then(res => {
                if (res.code == '200') {
                    context.commit('setDocumentClassList', res.data)
                } else {
                    this.$notify.error(res.msg)
                }
            })
        },
    },
    modules: {}
})
