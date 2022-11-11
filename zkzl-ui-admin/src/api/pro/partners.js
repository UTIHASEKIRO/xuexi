import request from '@/utils/request'

// 创建合作伙伴
export function createPartners(data) {
  return request({
    url: '/pro/partners/create',
    method: 'post',
    data: data
  })
}

// 更新合作伙伴
export function updatePartners(data) {
  return request({
    url: '/pro/partners/update',
    method: 'put',
    data: data
  })
}

// 删除合作伙伴
export function deletePartners(id) {
  return request({
    url: '/pro/partners/delete?id=' + id,
    method: 'delete'
  })
}

// 获得合作伙伴
export function getPartners(id) {
  return request({
    url: '/pro/partners/get?id=' + id,
    method: 'get'
  })
}

// 获得合作伙伴分页
export function getPartnersPage(query) {
  return request({
    url: '/pro/partners/page',
    method: 'get',
    params: query
  })
}

// 导出合作伙伴 Excel
export function exportPartnersExcel(query) {
  return request({
    url: '/pro/partners/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
