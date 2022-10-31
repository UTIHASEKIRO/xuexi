import request from '@/utils/request'

// 创建客户管理跟进
export function createCustomerManage(data) {
  return request({
    url: '/pro/customer-manage/create',
    method: 'post',
    data: data
  })
}

// 更新客户管理跟进
export function updateCustomerManage(data) {
  return request({
    url: '/pro/customer-manage/update',
    method: 'put',
    data: data
  })
}

// 删除客户管理跟进
export function deleteCustomerManage(id) {
  return request({
    url: '/pro/customer-manage/delete?id=' + id,
    method: 'delete'
  })
}

// 获得客户管理跟进
export function getCustomerManage(id) {
  return request({
    url: '/pro/customer-manage/get?id=' + id,
    method: 'get'
  })
}

// 获得客户管理跟进分页
export function getCustomerManagePage(query) {
  return request({
    url: '/pro/customer-manage/page',
    method: 'get',
    params: query
  })
}

// 导出客户管理跟进 Excel
export function exportCustomerManageExcel(query) {
  return request({
    url: '/pro/customer-manage/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
