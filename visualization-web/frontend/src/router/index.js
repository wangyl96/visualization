import { createRouter, createWebHashHistory } from 'vue-router'
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true,
  },
  {
    path: '/403',
    name: '403',
    component: () => import('@/views/403'),
    hidden: true,
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/404'),
    hidden: true,
  },
]
export const asyncRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    meta: {
      title: '首页',
      icon: 'home-4-line',
      affix: true,
    },
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/index'),
        meta: {
          title: '首页',
          icon: 'home-4-line',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/visualization',
    component: Layout,
    redirect: '/visualization',
    alwaysShow: true,
    meta: {
      title: '可视化数据',
      icon: 'apps-line',
    },
    children: [
      {
        path: '/visualization/overview',
        name: 'overview',
        component: () => import('@/views/visualization/index'),
        meta: {
          title: '数据总览',
          icon: 'table-2',
        },
      },
    ],
  },
]
const router = createRouter({
  history: createWebHashHistory(),
  routes: constantRoutes,
})

export default router
