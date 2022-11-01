import request from '@/utils/request'

// 创建客户公司信息
export function createCusUser(data) {
  return request({
    url: '/pro/cus-user/create',
    method: 'post',
    data: data
  })
}

// 更新客户公司信息
export function updateCusUser(data) {
  return request({
    url: '/pro/cus-user/update',
    method: 'put',
    data: data
  })
}

// 删除客户公司信息
export function deleteCusUser(id) {
  return request({
    url: '/pro/cus-user/delete?id=' + id,
    method: 'delete'
  })
}

// 获得客户公司信息
export function getCusUser(id) {
  return request({
    url: '/pro/cus-user/get?id=' + id,
    method: 'get'
  })
}

// 获得客户公司信息分页
export function getCusUserPage(query) {
  return request({
    url: '/pro/cus-user/pageCustomer',
    method: 'get',
    params: query
  })
}

// 导出客户公司信息 Excel
export function exportCusUserExcel(query) {
  return request({
    url: '/pro/cus-user/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}


// 禁用/启用 用户登录前端页面
export function updateUserStatus(query) {
  return request({
    url: '/system/user/update-status',
    method: 'put',
    data: query,
  })
}

// 重置密码
export function updateUserPassword(query) {
  return request({
    url: '/system/user/update-password',
    method: 'put',
    data: query,
  })
}
