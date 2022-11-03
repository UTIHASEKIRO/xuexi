import request from '@/utils/request'

// 创建产品参数
export function createductParameters(data) {
  return request({
    url: '/pro/duct-parameters/create',
    method: 'post',
    data: data
  })
}

// 更新产品参数
export function updateductParameters(data) {
  return request({
    url: '/pro/duct-parameters/update',
    method: 'put',
    data: data
  })
}

// 删除产品参数
export function deleteductParameters(id) {
  return request({
    url: '/pro/duct-parameters/delete?id=' + id,
    method: 'delete'
  })
}

// 获得产品参数
export function getductParameters(id) {
  return request({
    url: '/pro/duct-parameters/get?id=' + id,
    method: 'get'
  })
}

// 获得产品参数分页
export function getductParametersPage(query) {
  return request({
    url: '/pro/duct-final-parameters/valuePage',
    method: 'get',
    params: query
  })
}

// 导出产品参数 Excel
export function exportductParametersExcel(query) {
  return request({
    url: '/pro/duct-parameters/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
