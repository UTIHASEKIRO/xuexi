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
    url: '/pro/cus-user/page',
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
