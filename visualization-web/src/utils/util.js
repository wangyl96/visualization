export function timeFix () {
  const time = new Date()
  const hour = time.getHours()
  return hour < 9 ? '早上好' : hour <= 11 ? '上午好' : hour <= 13 ? '中午好' : hour < 20 ? '下午好' : '晚上好'
}

export function welcome () {
  const arr = ['休息一会儿吧', '准备吃什么呢?', '要不要打一把 DOTA', '我猜你可能累了']
  const index = Math.floor(Math.random() * arr.length)
  return arr[index]
}

/**
 * 触发 window.resize
 */
export function triggerWindowResizeEvent () {
  const event = document.createEvent('HTMLEvents')
  event.initEvent('resize', true, true)
  event.eventType = 'message'
  window.dispatchEvent(event)
}

export function handleScrollHeader (callback) {
  let timer = 0

  let beforeScrollTop = window.pageYOffset
  callback = callback || function () {}
  window.addEventListener(
    'scroll',
    event => {
      clearTimeout(timer)
      timer = setTimeout(() => {
        let direction = 'up'
        const afterScrollTop = window.pageYOffset
        const delta = afterScrollTop - beforeScrollTop
        if (delta === 0) {
          return false
        }
        direction = delta > 0 ? 'down' : 'up'
        callback(direction)
        beforeScrollTop = afterScrollTop
      }, 50)
    },
    false
  )
}

export function isIE () {
  const bw = window.navigator.userAgent
  const compare = (s) => bw.indexOf(s) >= 0
  const ie11 = (() => 'ActiveXObject' in window)()
  return compare('MSIE') || ie11
}

/**
 * Remove loading animate
 * @param id parent element id or class
 * @param timeout
 */
export function removeLoadingAnimate (id = '', timeout = 1500) {
  if (id === '') {
    return
  }
  setTimeout(() => {
    document.body.removeChild(document.getElementById(id))
  }, timeout)
}

/**
 * 按千分位格式化
 * @param num
 * @returns {string}
 */
export function formatNum (num) {
  if (num) {
    // console.log(num)
    // 将num中的$,去掉，将num变成一个纯粹的数据格式字符串
    // eslint-disable-next-line no-useless-escape
    num = num.toString().replace(/\$|\,/g, '')
    // 如果num不是数字，则将num置0，并返回
    if (num === '' || isNaN(num)) {
      return 'Not a Number ! '
    }
    // 如果num是负数，则获取她的符号
    var sign = num.indexOf('-') > 0 ? '-' : ''
    // 如果存在小数点，则获取数字的小数部分
    var cents = num.indexOf('.') > 0 ? num.substr(num.indexOf('.')) : ''
    cents = cents.length > 1 ? cents : '' // 注意：这里如果是使用change方法不断的调用，小数是输入不了的
    // 获取数字的整数数部分
    num = num.indexOf('.') > 0 ? num.substring(0, (num.indexOf('.'))) : num
    // 如果没有小数点，整数部分不能以0开头
    if (cents === '') {
      if (num.length > 1 && num.substr(0, 1) === '0') {
        return 'Not a Number ! '
      }
      // eslint-disable-next-line brace-style
    }
    // 如果有小数点，且整数的部分的长度大于1，则整数部分不能以0开头
    else { if (num.length > 1 && num.substr(0, 1) === '0') { return 'Not a Number ! ' } }
    // 针对整数部分进行格式化处理，这是此方法的核心，也是稍难理解的一个地方，逆向的来思考或者采用简单的事例来实现就容易多了
    /*
      也可以这样想象，现在有一串数字字符串在你面前，如果让你给他家千分位的逗号的话，你是怎么来思考和操作的?
      字符串长度为0/1/2/3时都不用添加
      字符串长度大于3的时候，从右往左数，有三位字符就加一个逗号，然后继续往前数，直到不到往前数少于三位字符为止
     */
    for (let i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) {
      num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3))
    }
    // 将数据（符号、整数部分、小数部分）整体组合返回
    return (sign + num + cents)
  } else {
    return 0
  }
}
