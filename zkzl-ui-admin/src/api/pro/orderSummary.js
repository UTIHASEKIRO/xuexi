import request from '@/utils/request'

// 创建订单汇总
export function createOrderSummary(data) {
  return request({
    url: '/pro/order-summary/create',
    method: 'post',
    data: data
  })
}

// 更新订单汇总
export function updateOrderSummary(data) {
  return request({
    url: '/pro/order-summary/update',
    method: 'put',
    data: data
  })
}

// 删除订单汇总
export function deleteOrderSummary(id) {
  return request({
    url: '/pro/order-summary/delete?id=' + id,
    method: 'delete'
  })
}

// 获得订单汇总
export function getOrderSummary(id) {
  return request({
    url: '/pro/order-summary/get?id=' + id,
    method: 'get'
  })
}

// 获得订单汇总分页
export function getOrderSummaryPage(query) {
  return request({
    url: '/pro/order-summary/page',
    method: 'get',
    params: query
  })
}

// 导出订单汇总 Excel
export function exportOrderSummaryExcel(query) {
  return request({
    url: '/pro/order-summary/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// /admin-api/pro/order-summary/pageOrderSummary
export function pageOrderSummary(query) {
  return request({
    url: '/pro/order-summary/pageOrderSummary',
    method: 'get',
    params: query,
  })
}

export function pageOrderCount(query) {
  return request({
    url: '/pro/order-summary/pageOrderCount',
    method: 'get',
    params: query,
  })
}