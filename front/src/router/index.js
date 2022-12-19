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
import AdminGoodsInfo from "@/views/Admin/AdminGoodsInfo";
import AdminGoodsMedia from "@/views/Admin/AdminGoodsMedium";
import AdminGoodsPublisher from "@/views/Admin/AdminGoodsPublisher";
import AdminGoodsTag from "@/views/Admin/AdminGoodsTag";
import AdminOrderSend from "@/views/Admin/AdminOrderSend";
import AdminOrderReceive from "@/views/Admin/AdminOrderReceive";
import AdminOrderFinished from "@/views/Admin/AdminOrderFinished";
import AdminGoodsMedium from "@/views/Admin/AdminGoodsMedium";

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
                path:'goodsinfo',
                bane:'GoodsInfo',
                component:AdminGoodsInfo
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
                path:'ordersend',
                bane:'OrderSend',
                component:AdminOrderSend
            },
            {
                path:'orderreceive',
                bane:'OrderReceive',
                component:AdminOrderReceive
            },
            {
                path:'orderfinished',
                bane:'OrderFinished',
                component:AdminOrderFinished
            }
            // {
            //     path: 'checkstudentinfo',
            //     name: 'CheckStudentInfo',
            //     component: CheckStudentInfo
            // },
            // {
            //     path: 'checkteacherinfo',
            //     name: 'CheckTeacherInfo',
            //     component: CheckTeacherInfo
            // },
            // {
            //     path: 'checkschool',
            //     name: 'CheckSchool',
            //     component: CheckSchool
            // },
            // {
            //     path: 'checkmajor',
            //     name: 'CheckMajor',
            //     component: CheckMajor
            // },
            // {
            //     path: 'checkbuilding',
            //     name: 'CheckBuilding',
            //     component: CheckBuilding
            // },
            // {
            //     path: 'checkclassroom',
            //     name: 'CheckClassroom',
            //     component: CheckClassroom
            // },
            // {
            //     path: 'checktime',
            //     name: 'CheckTime',
            //     component: CheckTime
            // },
            // {
            //     path: 'checkcourse',
            //     name: 'CheckCourse',
            //     component: CheckCourse
            // },
            // {
            //     path: 'addcourse',
            //     name: 'AddCourse',
            //     component: AddCourse
            // },
            // {
            //     path: 'editcourse/:id',
            //     name: 'EditCourse',
            //     component: EditCourse,
            //     props(params) {
            //         return {
            //             id: params.id
            //         }
            //     }
            // },
            // {
            //     path: 'checkapplication',
            //     name: 'CheckApplication',
            //     component: CheckApplication
            // },
            // {
            //     path: 'checkcourseopen',
            //     name: 'CheckCourseOpen',
            //     component: CheckCourseOpen
            // },
            // {
            //     path: 'checkstudentapply',
            //     name: 'CheckStudentApply',
            //     component: CheckStudentApply
            // },
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

