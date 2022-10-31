import request from '@/utils/request'

// 创建订单凭证信息
export function createEvidence(data) {
  return request({
    url: '/pro/evidence/create',
    method: 'post',
    data: data
  })
}

// 更新订单凭证信息
export function updateEvidence(data) {
  return request({
    url: '/pro/evidence/update',
    method: 'put',
    data: data
  })
}

// 删除订单凭证信息
export function deleteEvidence(id) {
  return request({
    url: '/pro/evidence/delete?id=' + id,
    method: 'delete'
  })
}

// 获得订单凭证信息
export function getEvidence(id) {
  return request({
    url: '/pro/evidence/get?id=' + id,
    method: 'get'
  })
}

// 获得订单凭证信息分页
export function getEvidencePage(query) {
  return request({
    url: '/pro/evidence/page',
    method: 'get',
    params: query
  })
}

// 导出订单凭证信息 Excel
export function exportEvidenceExcel(query) {
  return request({
    url: '/pro/evidence/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
