import request from '@/utils/request'

// 创建订单成本
export function createOrderCost(data) {
  return request({
    url: '/pro/order-cost/create',
    method: 'post',
    data: data
  })
}

// 更新订单成本
export function updateOrderCost(data) {
  return request({
    url: '/pro/order-cost/update',
    method: 'put',
    data: data
  })
}

// 删除订单成本
export function deleteOrderCost(id) {
  return request({
    url: '/pro/order-cost/delete?id=' + id,
    method: 'delete'
  })
}

// 获得订单成本
export function getOrderCost(id) {
  return request({
    url: '/pro/order-cost/get?id=' + id,
    method: 'get'
  })
}

// 获得订单成本分页
export function getOrderCostPage(query) {
  return request({
    url: '/pro/order-cost/page',
    method: 'get',
    params: query
  })
}

// 导出订单成本 Excel
export function exportOrderCostExcel(query) {
  return request({
    url: '/pro/order-cost/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
