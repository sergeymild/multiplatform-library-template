import { View, StyleSheet } from 'react-native';
import { RnLibView } from '../../src';

export default function App() {
  return (
    <View style={styles.container}>
      <RnLibView color="#32a852" style={styles.box} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
