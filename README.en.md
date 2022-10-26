# 快速启动
# 后端：
    先执行 mvn clean install package -Dmaven.test.skip=true  
    再执行 YudaoServerApplication (opens new window)类
# 前端：
# VUE2
    在 yudao-ui-admin 目录下，执行如下命令，进行启动：
    进入项目目录
    cd yudao-ui-admin
    安装 Yarn，提升依赖的安装速度
    npm install --global yarn
    安装依赖
    yarn install
    启动服务
    npm run dev

# VUE3
    在 yudao-ui-admin-vue3 目录下，执行如下命令，进行启动：
    进入项目目录
    cd yudao-ui-admin-vue3
    安装 pnpm，提升依赖的安装速度
    npm config set registry https://registry.npmjs.org
    npm install -g pnpm
    安装依赖
    pnpm install
    启动服务
    npm run dev
