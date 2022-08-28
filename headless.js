import notify from './notify'


const headlessTask = async ()=>{
    // setInterval(()=>{
    //     console.log('running')
    // },2000)
    await notify()
    console.log('headless running');
}



export default headlessTask