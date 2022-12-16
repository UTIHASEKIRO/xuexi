import request from '@/utils/request'

// 创建产品
export function createduct(data) {
  return request({
    url: '/pro/duct/create',
    method: 'post',
    data: data
  })
}

// 更新产品
export function updateduct(data) {
  return request({
    url: '/pro/duct/update',
    method: 'put',
    data: data
  })
}

// 删除产品
export function deleteduct(id) {
  return request({
    url: '/pro/duct/delete?id=' + id,
    method: 'delete'
  })
}

// 获得产品
export function getduct(params) {
  return request({
    url: '/pro/duct/get'  ,
    method: 'get',
    params
  })
}

// 获得产品分页
export function getductPage(query) {
  return request({
    url: '/pro/duct/page',
    method: 'get',
    params: query
  })
}

// 获得产品分页
export function getductList(query) {
  return request({
    url: '/pro/duct/list',
    method: 'get',
    params: query
  })
}

// 导出产品 Excel
export function exportductExcel(query) {
  return request({
    url: '/pro/duct/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}


export function updateShipped(query) {
  return request({
    url: '/pro/duct/updateShipped',
    method: 'get',
    params: query,
  })
}

