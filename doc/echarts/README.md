
#echarts / 官网: https://echarts.apache.org/examples/zh/index.html
# 一. 富文本
    # 主标题/副标题/图例可以使用富文本使展示效果更丰富, 具体使用方法如下
    
    # 标题:
     title: {
        text: this.pieData.barViewMap[0].platForm,
        left: 20,
        button: 100,
        subtext: [
          '{a|' + '¥' + this.pieData.barViewMap[0].num + '}' +
          '{b|' + '  |   ' + ratio + '}'
        ],
        subtextStyle: {
          rich: {
            a: {
              color: 'red'
            },
            b: {
              color: 'green'
            }
          }
        }
      },
      
      # 图例:
      legend: {
          type: 'scroll',
          orient: 'vertical',
          left: '40%',
          top: 55,
          itemWidth: 8,
          itemHeight: 8,
          data: [
            { name: '产', icon: 'circle' },
            { name: '寿', icon: 'circle' },
            { name: '健', icon: 'circle' },
            { name: '资', icon: 'circle' },
            { name: '金服', icon: 'circle' }
          ],
          textStyle: {
            rich: {
              a: {
                lineHeight: 10,
                width: 24,
                color: '#333'
              },
              b: {
                color: '#999',
                lineHeight: 10,
                width: 74
              },
              c: {
                color: '#333'
              }
            }
          },
          formatter: function (name) {
            const barViewMap = that.pieData.barViewMap[0]
            let ratio = ''
            let value = ''
            switch (name) {
              case '产':
                ratio = that.JudgePositiveNegative(((barViewMap.product / barViewMap.num) * 100).toFixed(2)) + '%'
                value = '¥' + barViewMap.product
                break
    
              case '寿':
                ratio = that.JudgePositiveNegative(((barViewMap.life / barViewMap.num) * 100).toFixed(2)) + '%'
                value = '¥' + barViewMap.life
                break
    
              case '健':
                ratio = that.JudgePositiveNegative(((barViewMap.health / barViewMap.num) * 100).toFixed(2)) + '%'
                value = '¥' + barViewMap.health
                break
    
              case '资':
                ratio = that.JudgePositiveNegative(((barViewMap.wealth / barViewMap.num) * 100).toFixed(2)) + '%'
                value = '¥' + barViewMap.wealth
                break
    
              case '金服':
                ratio = that.JudgePositiveNegative(((barViewMap.gold / barViewMap.num) * 100).toFixed(2)) + '%'
                value = '¥' + barViewMap.gold
                break
            }
            var arr = [
              '{a|' + name + '}' +
              '{b|' + '  |   ' + ratio + '}' +
              '{c|' + value + '}'
            ]
            return arr
          }
        }
    # 其中a, b即为富文本文字区域, 可以各自自定义数据和样式