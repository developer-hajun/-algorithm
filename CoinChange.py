now = 2780
print('- '+str(now)+' Won',end=' ')
value = now//500
now %= 500
print('- '+str(500)+' Won: '+str(value)+',',end=' ')
value = now//100
now %= 100
print('- '+str(100)+' Won: '+str(value)+',',end=' ')
value = now//50
now %= 50
print('- '+str(50)+' Won: '+str(value)+',',end=' ')
value = now//10
now %= 10
print('- '+str(10)+' Won: '+str(value)+',',end=' ')