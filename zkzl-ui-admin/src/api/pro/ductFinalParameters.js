import request from '@/utils/request'

// 创建产品固定参数
export function createductFinalParameters(data) {
  return request({
    url: '/pro/duct-final-parameters/create',
    method: 'post',
    data: data
  })
}

// 更新产品固定参数
export function updateductFinalParameters(data) {
  return request({
    url: '/pro/duct-final-parameters/update',
    method: 'put',
    data: data
  })
}

// 删除产品固定参数
export function deleteductFinalParameters(id) {
  return request({
    url: '/pro/duct-final-parameters/delete?id=' + id,
    method: 'delete'
  })
}

// 获得产品固定参数
export function getductFinalParameters(id) {
  return request({
    url: '/pro/duct-final-parameters/get?id=' + id,
    method: 'get'
  })
}

// 获得产品固定参数分页
export function getductFinalParametersPage(query) {
  return request({
    url: '/pro/duct-final-parameters/page',
    method: 'get',
    params: query
  })
}

// 导出产品固定参数 Excel
export function exportductFinalParametersExcel(query) {
  return request({
    url: '/pro/duct-final-parameters/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
