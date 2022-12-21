import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/views/Home";
import Login from "@/views/Login";

import Admin from "@/views/Admin/Admin";
import CheckCourse from "@/views/Admin/CheckCourse";
import AddCourse from "@/views/Admin/AddCourse";
import EditCourse from "@/views/Admin/EditCourse";
import CheckStudentInfo from "@/views/Admin/CheckStudentInfo";
import CheckTeacherInfo from "@/views/Admin/CheckTeacherInfo";
import CheckSchool from "@/views/Admin/CheckSchool";
import CheckMajor from "@/views/Admin/CheckMajor";
import CheckBuilding from "@/views/Admin/CheckBuilding";
import CheckClassroom from "@/views/Admin/CheckClassroom";
import CheckTime from "@/views/Admin/CheckTime";
import CheckApplication from "@/views/Admin/CheckApplication";
import CheckCourseOpen from "@/views/Admin/CheckCourseOpen";

import Student from "@/views/Student/Student";
import StudentChooseCourse from "@/views/Student/StudentChooseCourse";
import StudentCheckCourse from "@/views/Student/StudentCheckCourse";
import StudentInfo from "@/views/Student/StudentInfo";
import StudentApply from "@/views/Student/StudentApply";
import StudentViewApplication from "@/views/Student/StudentViewApplication";
import StudentViewTable from "@/views/Student/StudentViewTable";

import Teacher from "@/views/Teacher/Teacher";
import TeacherInfo from "@/views/Teacher/TeacherInfo";
import TeacherCourse from "@/views/Teacher/TeacherCourse";
import TeacherApplication from "@/views/Teacher/TeacherApplication";
import TeacherAddCourse from "@/views/Teacher/TeacherAddCourse";
import TeacherEditCourse from "@/views/Teacher/TeacherEditCourse";
import StudentDropCourse from "@/views/Student/StudentDropCourse";
import CheckStudentApply from "@/views/Admin/CheckStudentApply";
import StudentFinishedCourse from "@/views/Student/StudentFinishedCourse";
import Register from "@/views/Register";
import AdminMain from "@/views/Admin/AdminMain";
import AdminGoodsInfo from "@/views/Admin/AdminGoodsForSale";
import AdminGoodsMedia from "@/views/Admin/AdminGoodsMedium";
import AdminGoodsPublisher from "@/views/Admin/AdminGoodsPublisher";
import AdminGoodsTag from "@/views/Admin/AdminGoodsTag";
import AdminGoodsMedium from "@/views/Admin/AdminGoodsMedium";
import AdminGoodsForSale from "@/views/Admin/AdminGoodsForSale";
import AdminGoodsSoldOut from "@/views/Admin/AdminGoodsSoldOut";
import UserMain from "@/views/User/UserMain";
import UserAddress from "@/views/User/UserAddress";
import userBuyList from "@/views/User/UserBuyList";
import UserBuyList from "@/views/User/UserBuyList";
import UserOrder from "@/views/User/UserOrder";
import UserShop from "@/views/User/UserShop";
import UserGoodsDetail from "@/views/User/UserGoodsDetail";
import UserNotPaidOrder from "@/views/User/UserNotPaidOrder";
import UserUndeliverOrder from "@/views/User/UserUndeliverOrder";
import UserNotReceiveOrder from "@/views/User/UserNotReceiveOrder";
import UserFinishedOrder from "@/views/User/UserFinishedOrder";
import AdminOrderNotPaid from "@/views/Admin/AdminOrderNotPaid";
import AdminOrderUndeliver from "@/views/Admin/AdminOrderUndeliver";
import AdminOrderNotReceive from "@/views/Admin/AdminOrderNotReceive";
import AdminOrderFinished from "@/views/Admin/AdminOrderFinished";
import UserNeedComment from "@/views/User/UserNeedComment";
import UserAlreadyComment from "@/views/User/UserAlreadyComment";

export const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: "/:catchAll(.*)",
        redirect: '/login'
    }
]

export const admin_routes =
    {
        path: '/admin',
        name: 'Admin',
        component: AdminMain,
        children: [
            {
                path:'goodsforsale',
                bane:'GoodsForSale',
                component:AdminGoodsForSale
            },
            {
                path:'goodssoldout',
                bane: 'GoodsSoldOut',
                component: AdminGoodsSoldOut
            },
            {
                path:'goodsmedium',
                bane:'GoodsMedium',
                component:AdminGoodsMedium
            },
            {
                path:'goodspublisher',
                bane:'GoodsPublisher',
                component:AdminGoodsPublisher
            },
            {
                path:'goodstag',
                bane:'GoodsTag',
                component:AdminGoodsTag
            },
            {
                path: 'ordernotpaid',
                bane: 'OrderNotPaid',
                component: AdminOrderNotPaid
            },
            {
                path: 'orderundeliver',
                bane: 'OrderUndeliver',
                component: AdminOrderUndeliver
            },
            {
                path: 'ordernotreceive',
                bane: 'OrderNotReceive',
                component: AdminOrderNotReceive
            },
            {
                path: 'orderfinished',
                bane: 'OrderFinished',
                component: AdminOrderFinished
            }
        ]
    }
export const user_routes =
    {
        path: '/user',
        name: 'User',
        component: UserMain,
        children: [
            {
                path:'address',
                bane:'Address',
                component:UserAddress
            },
            {
                path:'buylist',
                bane:'BuyList',
                component:UserBuyList
            },
            {
                path:'order',
                bane:'Order',
                component:UserOrder
            },
            {
                path:'shop',
                bane:'Shop',
                component:UserShop
            },
            {
                path: 'goodsdetail',
                bane: 'GoodsDetail',
                component: UserGoodsDetail
            },
            {
                path: 'notpaidorder',
                bane: 'NotPaidOrder',
                component: UserNotPaidOrder
            },
            {
                path: 'undeliverorder',
                bane: 'UndeliverOrder',
                component: UserUndeliverOrder
            },
            {
                path: 'notreceiveorder',
                bane: 'NotReceiverOrder',
                component: UserNotReceiveOrder
            },
            {
                path: 'finishedorder',
                bane: 'FinishedOrder',
                component: UserFinishedOrder
            },
            {
                path: 'needcomment',
                bane: 'NeedComment',
                component: UserNeedComment
            },
            {
                path: 'alreadycomment',
                bane:'AlreadyComment',
                component: UserAlreadyComment
            }
        ]
    }
export const teacher_routes =
    {
        path: '/teacher',
        name: 'Teacher',
        component: Teacher,
        children: [
            {
                path: 'teacherinfo',
                name: 'TeacherInfo',
                component: TeacherInfo
            },
            {
                path: 'teachercourse',
                name: 'TeacherCourse',
                component: TeacherCourse
            },
            {
                path: 'teacherapplication',
                name: 'TeacherApplication',
                component: TeacherApplication
            },
            {
                path: 'teacheraddcourse',
                name: 'TeacherAddCourse',
                component: TeacherAddCourse
            },
            {
                path: 'teachereditcourse',
                name: 'TeacherEditCourse',
                component: TeacherEditCourse
            }
        ]
    }

export const student_routes =
    {
        path: '/student',
        name: 'Student',
        component: Student,
        children: [
            {
                path: 'studentinfo',
                name: 'StudentInfo',
                component: StudentInfo
            },
            {
                path: 'studentchoosecourse',
                name: 'StudentChooseCourse',
                component: StudentChooseCourse
            },
            {
                path: 'studentcheckcourse',
                name: 'StudentCheckCourse',
                component: StudentCheckCourse
            },
            {
                path: 'studentapply',
                name: 'StudentApply',
                component: StudentApply
            },
            {
                path: 'studentviewapplication',
                name: 'StudentViewApplication',
                component: StudentViewApplication
            },
            {
                path: 'studentviewtable',
                name: 'StudentViewTable',
                component: StudentViewTable
            },
            {
                path: 'studentdropcourse',
                name: 'StudentDropCourse',
                component: StudentDropCourse
            },
            {
                path: 'studentfinishedcourse',
                name: 'StudentFinishedCourse',
                component: StudentFinishedCourse
            }
        ]

    }

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    // history: createWebHashHistory(),
    routes
})

// router.beforeEach((to, from) => {
//     
//
//     //sessionStorage.setItem("user", JSON.stringify(res.data.data))
//     //
//     if (!sessionStorage.getItem('routes')) {
//         
//         sessionStorage.setItem('routes', JSON.stringify(router.getRoutes()));
//         //store.commit('change_routes', router.getRoutes())
//     }
//
//     let store_routes = JSON.parse(sessionStorage.getItem('routes'))
//     let now_routes = router.getRoutes()
//     if (now_routes.length < store_routes.length) {
//         router.addRoute(admin_routes)
//         store_routes.forEach(item => {
//             
//             
//             
//             
//
//             if (!now_routes.includes(item)) {
//                 router.addRoute({
//                         path: item.path,
//                         component: item.components.default.__file,
//                         name: item.name
//                     })
//                 // router.addRoute(item)
//             }
//         })
//     }
//     // if (router.getRoutes().find(item => item.name === to.name)) {
//     //     this.$store.routes_store.forEach(item => {
//     //         if (item.name === to.name) {}
//     //     })
//     //
//     // }
//     
//     
//     
//     // if (to.matched.length === 0) { router.push(to.path)}
//     //router.push(to.path)
//     return true
// })

// router.afterEach((to, from) => {
//     if (from.path === "/login") {
//         
//         
//         if (store.state.routes_store.length < router.getRoutes().length) {
//             store.commit('change_routes', router.getRoutes())
//         }
//         
//     }
// })

export default router

