import request from '@/utils/request'

// 创建订单
export function createOrder(data) {
  return request({
    url: '/pro/order/create',
    method: 'post',
    data: data
  })
}

// 更新订单
export function updateOrder(data) {
  return request({
    url: '/pro/order/update',
    method: 'put',
    data: data
  })
}

// 删除订单
export function deleteOrder(id) {
  return request({
    url: '/pro/order/delete?id=' + id,
    method: 'delete'
  })
}

// 获得订单
export function getOrder(id) {
  return request({
    url: '/pro/order/get?id=' + id,
    method: 'get'
  })
}

// 获得订单分页
export function getOrderPage(query) {
  return request({
    url: '/pro/order/page',
    method: 'get',
    params: query
  })
}

// 导出订单 Excel
export function exportOrderExcel(query) {
  return request({
    url: '/pro/order/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
