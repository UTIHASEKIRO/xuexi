import request from '@/utils/request'

// 创建订单商品关联表	
export function createOrderGoods(data) {
  return request({
    url: '/pro/order-goods/create',
    method: 'post',
    data: data
  })
}

// 更新订单商品关联表	
export function updateOrderGoods(data) {
  return request({
    url: '/pro/order-goods/update',
    method: 'put',
    data: data
  })
}

// 删除订单商品关联表	
export function deleteOrderGoods(id) {
  return request({
    url: '/pro/order-goods/delete?id=' + id,
    method: 'delete'
  })
}

// 获得订单商品关联表	
export function getOrderGoods(id) {
  return request({
    url: '/pro/order-goods/get?id=' + id,
    method: 'get'
  })
}

// 获得订单商品关联表	分页
export function getOrderGoodsPage(query) {
  return request({
    url: '/pro/order-goods/page',
    method: 'get',
    params: query
  })
}

// 导出订单商品关联表	 Excel
export function exportOrderGoodsExcel(query) {
  return request({
    url: '/pro/order-goods/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
