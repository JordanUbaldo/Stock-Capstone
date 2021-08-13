<script>
import { Line } from 'vue-chartjs';

export default {
    extends: Line,
    name: 'balance-over-time',
    props: {
        chartdata: {
            type: Object,
            default: null
        },
        options: {
            type: Object,
            default: null
        }
    },
    mounted() {
        // console.log(this.chartdata);
        this.renderChart(this.chartdata, this.options);
    }
}
</script>

<style>

</style>