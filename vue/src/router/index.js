import Vue from 'vue'
import VueRouter from 'vue-router'
import Cookies from 'js-cookie';

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: ()=>import("@/views/Login/Login")
  },
  {
    path: '/register',
    name: 'register',
    component: ()=>import("@/views/Login/Register")
  },
  {
    path: '/',
    name: 'home',
    component: ()=>import("@/views/Home/Home"),
    children:[
      {
        path: 'right',
        name: 'right',
        component: ()=>import("@/views/Home/RightsView")
      },
      {
        path: 'myself',
        name: 'myself',
        component: ()=>import("@/views/Home/MyselfView")
      },
      {
        path: 'document',
        name: 'document',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: ()=>import("@/views/Home/DocumentView")
      },
      {
        path: 'departmentmanage',
        name: 'departmentmanage',
        component: ()=>import("@/views/Department/DepartmentManageView")
      },
      {
        path: 'departmentadd',
        name: 'departmentadd',
        component: ()=>import("@/views/Department/DepartmentAddView")
      },
      {
        path: 'useradd',
        name: 'useradd',
        component: ()=>import("@/views/User/UserAddView")
      },
      {
        path: 'usermanage',
        name: 'usermanage',
        component: ()=>import("@/views/User/UserManageView")
      },
      {
        path: 'userEdit',
        name: 'userEdit',
        component: ()=>import("@/views/User/UserEditView")
      },
      {
        path: 'rightoperate',
        name: 'rightoperate',
        component: ()=>import("@/views/Right/RoleOperate")
      },
      {
        path: 'roleadd',
        name: 'roleadd',
        component: ()=>import("@/views/Right/RoleAdd")
      },
      {
        path: 'roleedit',
        name: 'roleedit',
        component: ()=>import("@/views/Right/RoleEdit")
      },
      {
        path: 'rolesearch',
        name: 'rolesearch',
        component: ()=>import("@/views/Right/RoleSearch")
      },
      {
        path: 'rolesearchmanage',
        name: 'rolesearchmanage',
        component: ()=>import("@/views/Right/RoleSearchManage")
      },
      {
        path: 'userabout',
        name: 'userabout',
        component: ()=>import("@/views/User/UserAboutView")
      },
      {
        path: 'departmentEdit',
        name: 'departmentEdit',
        component: ()=>import("@/views/Department/DepartmentEdit")
      },
      {
        path: 'departmentapplymanage',
        name: 'departmentapplymanage',
        component: ()=>import("@/views/Department/DepartmentApplyManage")
      },
      {
        path: 'departmentapply',
        name: 'departmentapply',
        component: ()=>import("@/views/Department/DepartmentApply")
      },
      {
        path: 'departmentView',
        name: 'departmentView',
        component: ()=>import("@/views/Department/DepartmentView")
      },
      {
        path: 'roleView',
        name: 'roleView',
        component: ()=>import("@/views/Right/RoleView")
      },
      {
        path: 'userResetPwd',
        name: 'userResetPwd',
        component: ()=>import("@/views/User/UserResetPwd")
      },
      {
        path: 'adminResetPwd',
        name: 'adminResetPwd',
        component: ()=>import("@/views/User/AdminResetPwd")
      },
      {
        path: 'documentclassadd',
        name: 'documentclassadd',
        component: ()=>import("@/views/Document/DocumentClassAdd")
      },
      {
        path: 'documentprocessmanage',
        name: 'documentprocessmanage',
        component: ()=>import("@/views/Document/DocumentClassOperate")
      },
      {
        path: 'documentClassEdit',
        name: 'documentClassEdit',
        component: ()=>import("@/views/Document/DocumentClassEdit")
      },
      {
        path: 'documentexamine',
        name: 'documentexamine',
        component: ()=>import("@/views/Document/DocumentExamine")
      },
      {
        path: 'documentpost',
        name: 'documentpost',
        component: ()=>import("@/views/Document/DocumentPost")
      },
      {
        path: 'documentClassView',
        name: 'documentClassView',
        component: ()=>import("@/views/Document/DocumentClassView")
      },
      {
        path: 'documentExamineView',
        name: 'documentExamineView',
        component: ()=>import("@/views/Document/DocumentExamineView")
      },
      {
        path: 'documentPassView',
        name: 'documentPassView',
        component: ()=>import("@/views/Document/DocumentPassView")
      },
      {
        path: 'maillist',
        name: 'maillist',
        component: ()=>import("@/views/User/MailListView")
      },
      {
        path: 'usersearch',
        name: 'usersearch',
        component: ()=>import("@/views/User/UserSearch")
      },
      {
        path: 'documentmyself',
        name: 'documentmyself',
        component: ()=>import("@/views/Document/DocumentMyselfView")
      },
      {
        path: 'documentmanage',
        name: 'documentmanage',
        component: ()=>import("@/views/Document/DocumentManage")
      },
    ]
  },
  {
    path: '*',
    name: 'view404',
    component: ()=>import("@/views/404")
  }
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=>{
  if(to.path==='/login'||to.path==='/register') return next()
  const user=Cookies.get('user')
  if(!user&&to.path!=='/login') return next('/login')
  next()
})
//先把VueRouter原型对象的push保存一份
const originPush = VueRouter.prototype.push
//重写push方法
VueRouter.prototype.push = function (location,resolve,reject) {
  //调用保存的push方法
  //但是保存的push方法是挂载在window的方法 所以要通过call修改this的指向
  if(resolve&&reject){
    originPush.call(this,location,resolve,reject);
  }else{
    originPush.call(this,location,()=>{},()=>{});
  }
}

export default router
