import Main from '@/views/Main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
    path: '/login',
    name: 'login',
    meta: {
        title: 'Login - 登录'
    },
    component: () => import('@/views/login.vue')
};

export const page404 = {
    path: '/*',
    name: 'error-404',
    meta: {
        title: '404-页面不存在'
    },
    component: () => import('@/views/error-page/404.vue')
};

export const page403 = {
    path: '/403',
    meta: {
        title: '403-权限不足'
    },
    name: 'error-403',
    component: () => import('@//views/error-page/403.vue')
};

export const page500 = {
    path: '/500',
    meta: {
        title: '500-服务端错误'
    },
    name: 'error-500',
    component: () => import('@/views/error-page/500.vue')
};

export const preview = {
    path: '/preview',
    name: 'preview',
    component: () => import('@/views/form/article-publish/preview.vue')
};

export const locking = {
    path: '/locking',
    name: 'locking',
    component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
    path: '/',
    name: 'otherRouter',
    redirect: '/home',
    component: Main,
    children: [
        { path: 'home', title: {i18n: 'home'}, name: 'home_index', component: () => import('@/views/home/home.vue') },
        { path: 'ownspace', title: '个人中心', name: 'ownspace_index', component: () => import('@/views/own-space/own-space.vue') },
        { path: 'order/:order_id', title: '订单详情', name: 'order-info', component: () => import('@/views/advanced-router/component/order-info.vue') }, // 用于展示动态路由
        { path: 'shopping', title: '购物详情', name: 'shopping', component: () => import('@/views/advanced-router/component/shopping-info.vue') }, // 用于展示带参路由
        { path: 'message', title: '消息中心', name: 'message_index', component: () => import('@/views/message/message.vue') }
    ]
};

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
    {
        path: '/user-manage',
        icon: 'key',
        name: 'access',
        title: '用户管理',
        component: Main,
        children: [
            { 
                path: 'searchableTable', 
                title: '用户列表', 
                name: 'user-list', 
                icon: 'search', 
                component: () => import('@/views/user-manage/searchable-table.vue') 
            },
            { 
                path: 'editableTable', 
                title: '权限管理', 
                name: 'user-access', 
                icon: 'edit', 
                component: () => import('@/views/user-manage/editable-table.vue') 
            }
        ]
    },
    {
        path: '/device-manage',
        icon: 'crop',
        name: 'device-manage',
        title: '设备管理',
        component: Main,
        children: [
            { 
                path: 'searchableTable', 
                title: '设备列表', 
                name: 'device-list', 
                icon: 'search', 
                component: () => import('@/views/device-manage/searchable-table.vue') 
            },
            { 
                path: 'editableTable', 
                title: '权限管理', 
                name: 'device-access', 
                icon: 'lock-combination', 
                component: () => import('@/views/device-manage/editable-table.vue') 
            }
        ]
    },
   /* {
        path: '/access-test',
        icon: 'lock-combination',
        title: '权限测试页',
        name: 'accesstest',
        access: 0,
        component: Main,
        children: [
            { path: 'index', title: '权限测试页', name: 'accesstest_index', access: 0, component: () => import('@/views/access/access-test.vue') }
        ]
    },*/
    {
        path: '/ad-manage',
        icon: 'social-buffer',
        name: 'ad-manage',
        title: '广告管理',
        component: Main,
        children: [
             { 
                path: 'searchableTable', 
                title: '广告查询/删除', 
                name: 'as-list', 
                icon: 'search', 
                component: () => import('@/views/ad-manage/searchable-table.vue') 
            },
            /*{
                path: 'md-editor',
                icon: 'pound',
                name: 'md-editor',
                title: 'Markdown编辑器',
                component: () => import('@/views/my-components/markdown-editor/markdown-editor.vue')
            },
            {
                path: 'image-editor',
                icon: 'crop',
                name: 'image-editor',
                title: '图片预览编辑',
                component: () => import('@/views/my-components/image-editor/image-editor.vue')
            },
            {
                path: 'draggable-list',
                icon: 'arrow-move',
                name: 'draggable-list',
                title: '可拖拽列表',
                component: () => import('@/views/my-components/draggable-list/draggable-list.vue')
            },
            {
                path: 'area-linkage',
                icon: 'ios-more',
                name: 'area-linkage',
                title: '城市级联',
                component: () => import('@/views/my-components/area-linkage/area-linkage.vue')
            },*/
            {
                path: 'file-upload',
                icon: 'android-upload',
                name: 'as-upload',
                title: '广告添加',
                component: () => import('@/views/my-components/file-upload/file-upload.vue')
            }
           /* {
                path: 'scroll-bar',
                icon: 'android-upload',
                name: 'scroll-bar',
                title: '滚动条',
                component: () => import('@/views/my-components/scroll-bar/scroll-bar-page.vue')
            },
            {
                path: 'count-to',
                icon: 'arrow-graph-up-right',
                name: 'count-to',
                title: '数字渐变',
                // component: () => import('@/views/my-components/count-to/count-to.vue')
                component: () => import('@/views/my-components/count-to/count-to.vue')
            },
            {
                path: 'split-pane-page',
                icon: 'ios-pause',
                name: 'split-pane-page',
                title: 'split-pane',
                component: () => import('@/views/my-components/split-pane/split-pane-page.vue')
            }*/
        ]
    },
    {
        path: '/message-manage',
        icon: 'android-checkbox',
        name: 'message-manage',
        title: '消息管理',
        component: Main,
        children: [
           /* { path: 'artical-publish', title: '消息', name: 'artical-publish', icon: 'compose', component: () => import('@/views/form/article-publish/article-publish.vue') },
            { path: 'workflow', title: '工作流', name: 'workflow', icon: 'arrow-swap', component: () => import('@/views/form/work-flow/work-flow.vue') }*/
            { path: 'mutative-router', title: '消息记录', name: 'message-list', icon: 'link', component: () => import('@/views/message-manage/mutative-router.vue') },
            { path: 'argument-page', title: '消息发布', name: 'message-upload', icon: 'android-send', component: () => import('@/views/message-manage/argument-page.vue') }

        ]
    },
    // {
    //     path: '/charts',
    //     icon: 'ios-analytics',
    //     name: 'charts',
    //     title: '图表',
    //     component: Main,
    //     children: [
    //         { path: 'pie', title: '饼状图', name: 'pie', icon: 'ios-pie', component: resolve => { require('@/views/access/access.vue') },
    //         { path: 'histogram', title: '柱状图', name: 'histogram', icon: 'stats-bars', component: resolve => { require('@/views/access/access.vue') }

    //     ]
    // },
    {
        path: '/health-manage',
        icon: 'ios-grid-view',
        name: 'health-manage',
        title: '健康服务',
        component: Main,
        children: [
            { path: 'artical-publish', title: '文章发布', name: 'artical-publish', icon: 'compose', component: () => import('@/views/health-manage/article-publish/article-publish.vue') },
            { path: 'workflow', title: '医疗百科', name: 'health-artical', icon: 'arrow-swap', component: () => import('@/views/log-manage/mutative-router.vue') }
        ]
    },
    {
        path: '/log-manage',
        icon: 'ios-infinite',
        name: 'log-manage',
        title: '日志管理',
        component: Main,
        children: [
            { path: 'mutative-router', title: '用户日志', name: 'user-log', icon: 'link', component: () => import('@/views/log-manage/mutative-router.vue') },
            { path: 'argument-page', title: '设备日志', name: 'device-log', icon: 'android-send', component: () => import('@/views/log-manage/argument-page.vue') }
        ]
    },
      {
        path: '/international',
        icon: 'earth',
        title: {i18n: 'international'},
        name: 'international',
        component: Main,
        children: [
            { path: 'index', title: {i18n: 'international'}, name: 'international_index', component: () => import('@/views/international/international.vue') }
        ]
    }
   /* {
        path: '/error-page',
        icon: 'android-sad',
        title: '错误页面',
        name: 'errorpage',
        component: Main,
        children: [
            { path: 'index', title: '错误页面', name: 'errorpage_index', component: () => import('@/views/error-page/error-page.vue') }
        ]
    }*/
];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
    loginRouter,
    otherRouter,
    preview,
    locking,
    ...appRouter,
    page500,
    page403,
    page404
];
