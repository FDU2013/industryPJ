import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/views/Home";
import Login from "@/views/Login";


import Register from "@/views/Register";
import AdminMain from "@/views/Admin/AdminMain";
import AdminGoodsPublisher from "@/views/Admin/AdminGoodsPublisher";
import AdminGoodsTag from "@/views/Admin/AdminGoodsTag";
import AdminGoodsMedium from "@/views/Admin/AdminGoodsMedium";
import AdminGoodsForSale from "@/views/Admin/AdminGoodsForSale";
import AdminGoodsSoldOut from "@/views/Admin/AdminGoodsSoldOut";
import UserMain from "@/views/User/UserMain";
import UserAddress from "@/views/User/UserAddress";
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
import AdminUserInfo from "@/views/Admin/AdminUserInfo";
import UserCenter from "@/views/User/UserCenter";

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
            },
            {
                path: 'userinfo',
                bane: 'UserInfo',
                component: AdminUserInfo
            }
        ]
    }
export const user_routes =
    {
        path: '/user',
        name: 'User',
        component: UserMain,
        redirect:'/user/shop',
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
            },
            {
                path: 'usercenter',
                bane: 'UserCenter',
                component: UserCenter
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

